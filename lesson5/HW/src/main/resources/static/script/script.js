$(document).ready(function(){
    $("form").submit(function(){
        $("input").each(function(index, obj){
            if($(obj).val() == "") {
                $(obj).remove();
            }
        });
    });

    $(".reset").bind("click", function() {
        $(':input').val('').removeAttr('value');
    });

        $('#form-add input').keyup(function() {

            var empty = false;
            $('#form-add input').each(function() {
                if ($(this).val().length == 0) {
                    empty = true;
                }
            });

            if (empty) {
                $('#form-add .btn-primary').attr('disabled', 'disabled');
            } else {
                $('#form-add .btn-primary').attr('disabled', false);
            }
        });
});