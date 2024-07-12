document.addEventListener('DOMContentLoaded', function() {
    const categorySelect = document.getElementById('categoriesSelect');
    const selectedCategoriesDiv = document.getElementById('selectedCategories');
    const hiddenInput = document.querySelector('input[name="categories"]');

    categorySelect.addEventListener('change', function() {
        selectedCategoriesDiv.innerHTML = '';
        
        const selectedOptions = [...categorySelect.options].filter(option => option.selected);
        const selectedValues = selectedOptions.map(option => option.value);
        
        hiddenInput.value = selectedValues.join(",");
        
        selectedOptions.forEach(option => {
            const categorySpan = document.createElement('span');
            categorySpan.textContent = option.text;
            selectedCategoriesDiv.appendChild(categorySpan);
        });
    });
});