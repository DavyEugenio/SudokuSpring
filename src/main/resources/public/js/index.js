let listCells = [];
let iAttempts = document.getElementById("iAttempts");
let board = document.getElementById("board");
createCells();
function createCells(){
	for(let i =0; i<9;i++){
		let tr = document.createElement("TR");
		for(let j = 0; j< 9; j++){
			let input = document.createElement("INPUT");
			if(i>0 & i%3 == 0)
				input.classList.add("vert-border");
			if(j<8 & j%3 == 2)
				input.classList.add("hori-border");
			let index = (i*9+j);
			input.id = "cell" + index;
			input.name = "cell" + i;
			input.max = 9;
			input.min = 0;
			input.type = "number";
			input.value = 0;
			input.onchange = () => {
				if (this.value > this.max)
				this.value = 9;
			}
			let td = document.createElement("TD");
			td.id = "cell" + index;
			td.appendChild(input);
			tr.appendChild(td);
		}
		board.appendChild(tr);
	}
}//@RequestBody List<Integer> cells, @RequestBody int attempts

function getCellValues() {
	let values = []; 
	for(let i=0; i<81; i++){
		let value = document.getElementById("cell" + i).value; 
		values += (value <= 9 && value >= 0)? value: 0;
	}
	return values;
}

function formatRequestBody() {
	let rb = {};
	rb.cells = getCellValues();
	rb.attempts = iAttempts.value;
	return rb;
}

function getResult() {
	makeRequest("POST", "", function() {
		let responseJson = JSON.parse(this.responseText);
		if (this.readyState == 4 && this.status == 200) {
			console.log(responseJson)
		} else {
			let rj = JSON.parse(this.responseText);
			errorHandler(rj);
		}
	}, formatRequestBody());
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