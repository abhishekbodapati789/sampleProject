(function ($, Granite) {
    $(document).on("dialog-ready", function () {
        $(".cq-dialog-submit").click(function (event) {
            let missingFields = [];

            // Iterate through all form elements inside the cq:dialog
            $("[name]").each(function () {
                let fieldName = $(this).attr("name");
                let fieldValue = null;
                
                // Handle different field types
                if ($(this).is("input[type='text'], input[type='number'], input[type='email'], input[type='password'], textarea, select")) {
                    fieldValue = $(this).val()?.trim();
                } 
                // Coral TextField, Select, TextArea
                else if ($(this).is("coral-textfield, coral-textarea, coral-select")) {
                    fieldValue = $(this)[0].value?.trim();
                } 
                // Coral Checkbox/Radio (must be checked)
                else if ($(this).is("coral-checkbox, coral-radio")) {
                    fieldValue = $(this).is(":checked") ? "checked" : "";
                } 
                // Coral Multi-field (should have at least one item)
                else if ($(this).closest("coral-multifield").length > 0) {
                    fieldValue = $(this).closest("coral-multifield").find("coral-multifield-item").length > 0 ? "filled" : "";
                } 
                // Rich Text Editor (RTE)
                else if ($(this).hasClass("cq-RichText-editable")) {
                    fieldValue = $(this).text()?.trim();
                }

                // Check if the field is empty
                if (!fieldValue) {
                    missingFields.push(fieldName.replace("./", "")); // Extract field name without './'
                }
            });

            if (missingFields.length > 0) {
                event.preventDefault(); // Stop form submission

                let errorMessage =
                    missingFields.length === $("[name]").length
                        ? "None of the fields are authored in the cq:dialog."
                        : missingFields.length === 1
                        ? missingFields[0] + " is not authored in the cq:dialog."
                        : missingFields.join(", ") + " are not authored in the cq:dialog.";

                // Remove any existing error dialog
                $("#error-dialog").remove();

                // Create and show Coral UI error dialog
                let errorDialog = new Coral.Dialog().set({
                    id: "error-dialog",
                    variant: "warning",
                    header: { innerHTML: "Validation Error" },
                    content: { innerHTML: errorMessage },
                    footer: {
                        innerHTML: '<button is="coral-button" variant="primary" coral-close>OK</button>',
                    },
                });

                document.body.appendChild(errorDialog);

                Coral.commons.ready(errorDialog, function () {
                    errorDialog.show();
                });
            }
        });
    });
})(jQuery, Granite);
