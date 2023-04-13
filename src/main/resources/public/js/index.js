let listCells = [];
let iAttempts = document.getElementById("iAttempts");
let iFile = document.getElementById("iFile");
let resultAttempts = document.getElementById("resultAttempts");
let board = document.getElementById("board");

iFile.onchange = async function(e) {
	e.preventDefault();
	lerArquivo(inputGrafo.files[0]);
	inputGrafo.value = "";

}

function sendFile() {
	iFile.click();
}

iFile.onchange = async function(e) {
	e.preventDefault();
	readFile(iFile.files[0]);
	iFile.value = "";
}

function readFile(file) {
	let reader = new FileReader();
	reader.onload = onReaderLoad;
	reader.readAsText(file);
}

function onReaderLoad(event) {
	let readResult = JSON.parse(event.target.result);
	getResult(readResult);
	for (let i = 0; i < 81; i++) {
		if (readResult.cells[i] != 0)
			document.getElementById("cell-" + i).style.backgroundColor = "#ccc";
	}
}

function initCell(index) {
	let cell = document.getElementById("cell-" + index);
	cell.style.backgroundColor = "initial";
	cell.value = "";
	cell.disabled = false;
}

function reset() {
	for (let i = 0; i < 81; i++) {
		initCell(i);
	}
	iAttempts.value = 10;
	resultAttempts.innerHTML = "-";
	resultAttempts.style.backgroundColor = "initial";
}

function createCells() {
	for (let i = 0; i < 9; i++) {
		let tr = document.createElement("TR");
		for (let j = 0; j < 9; j++) {
			let input = document.createElement("INPUT");
			if (i > 0 & i % 3 == 0)
				input.classList.add("vert-border");
			if (j < 8 & j % 3 == 2)
				input.classList.add("hori-border");
			let index = (i * 9 + j);
			input.id = "cell-" + index;
			input.name = "cell-" + index;
			input.max = 9;
			input.min = 1;
			input.type = "number";
			input.value = "";
			input.onchange = () => {
				if (!(input.value >= 1 && input.value <= 9 && Number(input.value))) {
					if (input.value != "") {
						input.value = "";
						alert("Insira valor valido");
					}
				}
			}
			let td = document.createElement("TD");
			td.id = "td-" + index;
			td.appendChild(input);
			tr.appendChild(td);
		}
		board.appendChild(tr);
	}
}

function getCellValues() {
	let values = [];
	for (let i = 0; i < 81; i++) {
		let cell = document.getElementById("cell-" + i);
		let value = cell.value;
		if (value >= 0 && value <= 9 && Number(value)) {
			values.push(value);
			cell.style.backgroundColor = "#ccc";
		} else {
			values.push(0);
		}

	}
	return values;
}

function formatRequestBody() {
	let rb = {};
	rb.cells = getCellValues();
	rb.attempts = iAttempts.value;
	return rb;
}

function sendBoardValues() {
	getResult(formatRequestBody());
}

function getResult(rq) {
	makeRequest("POST", "", function() {
		let rj = JSON.parse(this.responseText);
		if (this.readyState == 4 && this.status == 200) {
			fillResultInBoard(rj);
		} else {
			errorHandler(rj);
		}
	}, rq);
}



function fillResultInBoard(result) {
	resultAttempts.innerHTML = result.attempts;
	for (let i = 0; i < 81; i++) {
		let cell = document.getElementById("cell-" + i);
		let value = result.cells[i];
		if (value < 1 || value > 9)
			cell.value = "";
		else
			cell.value = value;
		cell.disabled = true;
	}
	alert("Resultado " + (result.solved ? "" : "nao") + " encontrado apos " + result.attempts + " tentativas");
	resultAttempts.innerHTML = result.attempts;
	resultAttempts.style.backgroundColor = result.solved ? "#0F0" : "#F00";
}

function makeRequest(method, url, onloadend, data) {
	let xhr = new XMLHttpRequest();
	xhr.open(method, "http://localhost:8080/sudoku" + url, true);
	xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	if (data) {
		xhr.send(JSON.stringify(data));
	} else {
		xhr.send();
	}
	xhr.onloadend = onloadend;
}