(function (document, $) {
    "use strict";

    $(document).on("foundation-contentloaded", function (e) {
        checkboxShowHideHandler($(".cq-dialog-checkbox-showhide", e.target));
    });

    $(document).on("change", ".cq-dialog-checkbox-showhide", function () {
        checkboxShowHideHandler($(this));
    });

    function checkboxShowHideHandler(elements) {
        elements.each(function () {
            const $element = $(this);
            const targetSelector = $element.data("cqDialogCheckboxShowhideTarget");
            const $target = $(targetSelector);

            if ($target.length > 0) {
                if ($element.is("coral-checkbox")) {
                    Coral.commons.ready(this, function (component) {
                        toggleVisibility(component.checked, $target);
                        component.on("change", function () {
                            toggleVisibility(component.checked, $target);
                        });
                    });
                } else {
                    toggleVisibility($element.is(":checked"), $target);
                }
            }
        });
    }

    function toggleVisibility(checked, $target) {
        if (checked) {
            $target.removeClass("hide");
        } else {
            $target.addClass("hide");
        }
    }
})(document, Granite.$);
