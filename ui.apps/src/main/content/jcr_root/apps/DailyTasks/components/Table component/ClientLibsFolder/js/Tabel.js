document.addEventListener("DOMContentLoaded", function () {
    console.log("Table Component Loaded!");

    // Example: Add a new row dynamically
    function addRow(data) {
        let table = document.querySelector(".custom-table tbody");
        let newRow = document.createElement("tr");

        data.forEach(cellData => {
            let cell = document.createElement("td");
            cell.innerHTML = cellData;
            newRow.appendChild(cell);
        });

        table.appendChild(newRow);
    }

    // Example Usage (Modify or remove if not needed)
    // addRow(["Dynamic 1", "Dynamic 2", "Dynamic 3"]);
});
