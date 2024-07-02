
function incrementValue(e) {
    e.preventDefault();
    var fieldName = $(e.target).data('field');
    var parent = $(e.target).closest('.product-quantity');
    var currentVal = parseInt(parent.find('input[name=' + fieldName + ']').val(), 10);

    if (!isNaN(currentVal)) {
        parent.find('input[name=' + fieldName + ']').val(currentVal + 1);
    } else {
        parent.find('input[name=' + fieldName + ']').val(0);
    }
}

function decrementValue(e) {
    e.preventDefault();
    var fieldName = $(e.target).data('field');
    var parent = $(e.target).closest('.product-quantity');
    var currentVal = parseInt(parent.find('input[name=' + fieldName + ']').val(), 10);

    if (!isNaN(currentVal) && currentVal > 0) {
        parent.find('input[name=' + fieldName + ']').val(currentVal - 1);
    } else {
        parent.find('input[name=' + fieldName + ']').val(0);
    }
}

$('.product-quantity').on('click', '.button-plus', function (e) {
    incrementValue(e);   
});

$('.product-quantity').on('click', '.button-minus', function (e) {
    decrementValue(e);
});

const productValidator = new JustValidate('#product-form');
const productForm = document.getElementById('product-form');
const backToHomepage = document.getElementById('back-to-homepage');
const modal = new bootstrap.Modal(document.getElementById('staticBackdrop'));

productValidator.addField("#upload-product-image",[
    {
        rule: 'minFilesCount',
        value: 1,
        errorMessage: 'Please upload an image',
      },
      {
        rule: 'files',
        errorMessage: 'Invalid file upload',
        value: {
          files: {
            extensions: ['jpeg', 'jpg', 'png','JPG','PNG','JPEG'],
            maxSize: 2097152,
            minSize: 10240,            
          },
        },
      },      
]).onSuccess((event) => {
    productForm.submit();
});
