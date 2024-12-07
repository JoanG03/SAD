let tablero = [];
let tableroVisual = document.getElementById('tablero');
let temporizadorElement = document.getElementById('temporizador');
let banderasElement = document.getElementById('banderas');
let tiempo = 0;
let temporizador; 
let bombasMarcadas = 0;
let banderas = 0;
let celdasPorLimpiar;
function iniciarTemporizador() {
    if (temporizador) {
        clearInterval(temporizador);
    }
    tiempo = 0;
    temporizadorElement.innerHTML = 'Tiempo: ' + tiempo; 

    temporizador = setInterval(function() { 
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
    banderas = bombs;
    banderasElement.innerHTML = 'Banderas: ' + banderas; 
    tableroVisual.style.gridTemplateColumns = columnas;
    iniciarTemporizador();
    tablero = crearTablero(size, bombs);
    renderTablero(size, bombs);
    celdasPorLimpiar = size * size - bombs;
}

function crearTablero(size, bombs) {
    // Crear tablero vacío
    let tablero = new Array(size); // Inicializamos el array principal con tamaño 'size'.
    for (let i = 0; i < size; i++) {
        tablero[i] = new Array(size); // Inicializamos cada fila como un nuevo array de tamaño 'size'.
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
function marcarBandera(row, col, bombs) {
    let maxBanderas = bombs;
    let cell = document.querySelector(`.cell[data-row='${row}'][data-col='${col}']`);
    if (cell.classList.contains('open')) return; // No se puede marcar celdas abiertas

    // Alternar entre marcar y desmarcar bandera
    if (cell.classList.contains('flag') && (banderas < maxBanderas)) {
        cell.classList.remove('flag');
        cell.textContent = ''; // Remover la bandera
        banderas++;
    } else if (!cell.classList.contains('flag') && banderas > 0){
        cell.classList.add('flag');
        cell.textContent = '🚩'; // Colocar la bandera
        banderas--; 
    }
    if(bombasMarcadas == bombs){
        detenerTemporizador();
    }
    banderasElement.innerHTML = 'Banderas: ' + banderas;
}

function renderTablero(size, bombs) {
    
    for (let i = 0; i < size; i++) {
        for (let j = 0; j < size; j++) {
            let cell = document.createElement('div');
            cell.className = 'cell';
            cell.dataset.row = i;
            cell.dataset.col = j;
            cell.addEventListener('click', () => openCell(i, j, size));
            cell.addEventListener('contextmenu', function(event) {
                event.preventDefault(); // Evitar el menú contextual del navegador
                marcarBandera(i, j, bombs);
            });
            tableroVisual.appendChild(cell);
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
        if (value === 1) {
            cell.classList.add('numero-1');
        } else if (value === 2) {
            cell.classList.add('numero-2');
        } else if (value === 3) {
            cell.classList.add('numero-3');
        } else if (value === 4) {
            cell.classList.add('numero-4');
        } else if (value === 5) {
            cell.classList.add('numero-5');
        } else if (value === 6) {
            cell.classList.add('numero-6');
        } else if (value === 7) {
            cell.classList.add('numero-7');
        } else if (value === 8) {
            cell.classList.add('numero-8');
        }
    }
s
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
    celdasPorLimpiar--;
    if(celdasPorLimpiar === 0){
        detenerTemporizador();
        alert('¡Has Ganado! Reinicia el juego para volver a jugar.');
    }
} 

function revelarTablero(size) {
    for (let i = 0; i < size; i++) {
        for (let j = 0; j < size; j++) {
            let cell = document.querySelector(`.cell[data-row='${i}'][data-col='${j}']`);
            if (cell && !cell.classList.contains('open')) {
                cell.classList.add('open');
                let value = tablero[i][j];
                if (value === 0) {
                    cell.textContent = ''; // Si el valor es 0, la celda se deja vacía.
                } else if (value === 'B') {
                    cell.textContent = '💣'; // Si el valor es una bomba ('B'), se muestra el emoji de bomba.
                } else {
                    cell.textContent = value; // En cualquier otro caso (un número), se muestra el valor directamente.
                    if (value === 1) {
                        cell.classList.add('numero-1');
                    } else if (value === 2) {
                        cell.classList.add('numero-2');
                    } else if (value === 3) {
                        cell.classList.add('numero-3');
                    } else if (value === 4) {
                        cell.classList.add('numero-4');
                    } else if (value === 5) {
                        cell.classList.add('numero-5');
                    } else if (value === 6) {
                        cell.classList.add('numero-6');
                    } else if (value === 7) {
                        cell.classList.add('numero-7');
                    } else if (value === 8) {
                        cell.classList.add('numero-8');
                    }
                }
            }
        }
    }
}
