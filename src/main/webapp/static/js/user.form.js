/**
 * Created by mithundas on 11/30/14.
 */
$(function() {
    addValidationErrorClassesToForm();

    function addValidationErrorClassesToForm() {
        $("form").find(".form-group").each(function() {
            var errorMessage = $(this).find(".help-block").text();

            if (errorMessage) {
                $(this).addClass("has-error");
            }
        })
    }
})
