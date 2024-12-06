let tablero = [];
let tableroVisual = document.getElementById('tablero');
let temporizadorElement = document.getElementById('temporizador');
let tiempo = 0; // Definir tiempo fuera de la función

function iniciarTemporizador() {
    tiempo = 0;
    temporizadorElement.innerHTML = 'Tiempo: ' + tiempo; 

    let temporizador = setInterval(function() { 
        tiempo++; // Aumentar tiempo
        temporizadorElement.innerHTML = 'Tiempo: ' + tiempo; 
    }, 1000); 
}

function detenerTemporizador() {
    clearInterval(temporizador);
}

function initJuego(size, bombs) {
    tableroVisual.innerHTML = ''; // Limpiar el tablero
    let columnas = Array(size).fill("25px").join(" ");
    tableroVisual.style.gridTemplateColumns = columnas;
    iniciarTemporizador();
    tablero = crearTablero(size, bombs);
    renderTablero(size);
}

function crearTablero(size, bombs) {
    // Crear tablero vacío
    let tablero = new Array(size); // Inicializamos el arreglo principal con tamaño 'size'.
    for (let i = 0; i < size; i++) {
        tablero[i] = new Array(size); // Inicializamos cada fila como un nuevo arreglo de tamaño 'size'.
        for (let j = 0; j < size; j++) {
            tablero[i][j] = 0; // Asignamos directamente el valor 0 a cada celda.
        }
    }
    // Colocar bombas aleatoriamente
    let bombCount = 0;
    while (bombCount < bombs) {
        let row = Math.floor(Math.random() * size);
        let col = Math.floor(Math.random() * size);
        if (tablero[row][col] == 0) {
            tablero[row][col] = 'B';
            bombCount++;

            // Incrementar conteo de celdas adyacentes
            for (let i = -1; i <= 1; i++) {
                for (let j = -1; j <= 1; j++) {
                    if (row + i >= 0 && row + i < size && col + j >= 0 &&  col + j < size && tablero[row + i][col + j] !== 'B' ){
                        tablero[row + i][col + j]++;
                    }
                }
            }
        }
    }
    return tablero;
}

function renderTablero(size) {
    for (let i = 0; i < size; i++) {
        for (let j = 0; j < size; j++) {
            let cell = document.createElement('div');
            cell.className = 'cell';
            cell.dataset.row = i;
            cell.dataset.col = j;
            cell.addEventListener('click', () => openCell(i, j));
            tableroVisual.appendChild(cell);
        }
    }
}

function openCell(row, col) {
    let cell = document.querySelector(`.cell[data-row='${row}'][data-col='${col}']`);
    if (!cell || cell.classList.contains('open')) return;

    cell.classList.add('open');
    let value = tablero[row][col];
    if (value === 0) {
        cell.textContent = ''; // Si el valor es 0, la celda se deja vacía.
    } else {
        cell.textContent = value; // Si no, se muestra el valor en la celda.
    }
    
    if (value === 'B') {
        cell.textContent = '💣';
        alert('¡Perdiste! Reinicia el juego para intentarlo de nuevo.');
        revelarTablero();
        detenerTemporizador();
    } else if (value === 0) {
        // Abrir automáticamente celdas vacías adyacentes
        for (let i = -1; i <= 1; i++) {
            for (let j = -1; j <= 1; j++) {
                if (i !== 0 || j !== 0) {
                    openCell(row + i, col + j);
                }
            }
        }
    }
}

function openCell(row, col, size) {
    let cell = document.querySelector(`.cell[data-row='${row}'][data-col='${col}']`);
    if (!cell || cell.classList.contains('open') || cell.textContent === '🚩') return;
    cell.classList.add('open');
    let value = tablero[row][col];
    if (value === 0) {
        cell.textContent = ''; // Si el valor es 0, la celda se deja vacía.
    } else {
        cell.textContent = value; // Si no, se muestra el valor en la celda.
    }

    if (value === 'B') {
        cell.textContent = '💣';
        alert('¡Perdiste! Reinicia el juego para intentarlo de nuevo.');
        revelarTablero(size);
        detenerTemporizador();
    } else if (value === 0) {
        // Abrir automáticamente celdas vacías adyacentes
        for (let i = -1; i <= 1; i++) {
            for (let j = -1; j <= 1; j++) {
                if (i !== 0 || j !== 0) {
                    openCell(row + i, col + j);
                }
            }
        }
    }
} 
