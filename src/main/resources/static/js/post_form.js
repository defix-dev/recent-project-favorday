$(document).ready(() => {
    $("#post-form").submit((e) => {
        function toBoolean(checkboxValue) {
            return checkboxValue == null ? false : true;
        }

        e.preventDefault();
        const formData = new FormData(e.target);
        const data = {
            title: formData.get("title-field"),
            shortPart: formData.get("short-part-field"),
            content: formData.get("content-field"),
            settings: {
                anonymous: toBoolean(formData.get("anonymous-param")),
                disallowComments: toBoolean(formData.get("disallow-comments-param")),
                disallowRate: toBoolean(formData.get("disallow-rate-param"))
            }
        };


        $.ajax({
            url: `post_form/create_post?confirm=${toBoolean(formData.get("confirm-value"))}`,
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json"
        });
    });
});