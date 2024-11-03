// Llama a la función cuando el DOM esté completamente cargado
document.addEventListener('DOMContentLoaded', () => {
    getTask();
});

let tasks = [];
let filtered = [];
let tbody = document.querySelector(".tbody")

async function getTask() {
    const url = 'http://localhost:8081/api';
    const respuesta = await fetch(url);
    const tareas = await respuesta.json();
    tasks = tareas;
    viewTasks();
}

let filtros = document.querySelectorAll('#filtros input[type="radio"]');

filtros.forEach(filtro => {
    filtro.addEventListener('input', filtrarTareas);
});


function filtrarTareas(e) {
    const filtro = e.target.value;

    if (filtro == "1") {
        filtered = tasks.filter(task => task.estadoTarea === "COMPLETA");
    } else if (filtro == "0") {
        filtered = tasks.filter(task => task.estadoTarea === "PENDIENTE");
    } else {
        filtered = [];
    }

    viewTasks();
}

const viewTasks = () => {
    limpiarTareas()
    let arrayFiltros = filtered.length ? filtered : tasks;

    arrayFiltros.forEach(task => {
        const row = document.createElement('tr');

        const nameCell = document.createElement('td');
        nameCell.textContent = task.nombre;
        row.appendChild(nameCell);

        const fechaInicioCell = document.createElement('td');
        fechaInicioCell.textContent = task.fechaInicio;
        row.appendChild(fechaInicioCell);

        const fechaFinCell = document.createElement('td');
        fechaFinCell.textContent = task.fechaFin;
        row.appendChild(fechaFinCell);

        const estadoCell = document.createElement('td');
        estadoCell.textContent = task.estadoTarea;
        row.appendChild(estadoCell);

        const actionsCell = document.createElement('td');

        const editLink = document.createElement('a');
        editLink.href = `/tarea/editar/${task.id}`;
        editLink.className = 'edit-button';

        const editButton = document.createElement('button');
        editButton.type = 'button';
        editButton.className = 'icon-button';
        editButton.innerHTML = `
            <svg class="edit-icon" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" d="M15.232 5.232a3 3 0 00-4.414 0L2.25 12.514v4.737a1.125 1.125 0 001.125 1.125h4.737l8.568-8.568a3 3 0 000-4.414z"></path>
            </svg>
            Editar
        `;
        editLink.appendChild(editButton);
        actionsCell.appendChild(editLink);

        const buttonSeparator = document.createElement('span');
        buttonSeparator.className = 'button-separator';
        actionsCell.appendChild(buttonSeparator);

        const deleteForm = document.createElement('form');
        deleteForm.action = `/tarea/eliminar/${task.id}`;
        deleteForm.method = 'post';
        deleteForm.style.display = 'inline';

        const deleteButton = document.createElement('button');
        deleteButton.type = 'submit';
        deleteButton.className = 'icon-button';
        deleteButton.innerHTML = `
            <svg class="delete size-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"></path>
            </svg>
            Eliminar
        `;
        deleteForm.appendChild(deleteButton);
        actionsCell.appendChild(deleteForm);

        row.appendChild(actionsCell);

        tbody.appendChild(row);
    });
};

    function limpiarTareas() {
        const table = document.querySelector(".tbody");
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }
    }