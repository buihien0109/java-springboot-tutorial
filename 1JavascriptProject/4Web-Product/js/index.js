// Truy cập vào các thành phần
const productListEl = document.querySelector(".product-list");
const seachFormInputEl = document.querySelector(".seach-form-input");
const seachFormBtnEl = document.querySelector(".seach-form-button");

// Tìm kiếm sản phẩn
const seachProduct = (term) => {
    // Lọc sản phẩn theo title
    let productFilter = products.filter((c) => c.title.toLowerCase().includes(term.toLowerCase()));
    renderProduct(productFilter);
};

// Tìm kiếm sản phẩn với ô input
seachFormInputEl.addEventListener("keydown", function (e) {
    if (e.keyCode == 13) {
        let term = e.target.value;
        if (term == "") {
            alert("Từ khóa không được để trống");
            return;
        }
        seachProduct(term);
    }
});

// Tìm kiếm sản phẩn với button
seachFormBtnEl.addEventListener("click", function () {
    let term = seachFormInputEl.value;
    if (term == "") {
        alert("Từ khóa không được để trống");
        return;
    }
    seachProduct(term);
});


// Render sản phẩn ra ngoài giao diện
const renderProduct = (arr) => {
    productListEl.innerHTML = "";

    if(arr.length == 0) {
        productListEl.innerHTML = `
            <div class="com-md-12">
                <h2 class="fs-3 mt-3">Danh sách sản phẩm trống</h2>
            </div>
        `;
        return;
    }

    for (let i = 0; i < arr.length; i++) {
        const p = arr[i];
        productListEl.innerHTML += `
            <div class="col-md-3">
                <a href="./detail.html?id=${p.id}">
                    <div class="product-item shadow-sm rounded mb-4">
                        <div class="product-item-image">
                            <img src="${p.images[0]}"
                                alt="${p.name}">
                        </div>
                        <div class="product-item-info p-3">
                            <h2 class="fs-5 mb-4 text-dark">${p.name}</h2>
                            <div class="d-flex justify-content-between align-items-center fw-light text-black-50">
                                <p class="type fs-5 text-danger fw-normal">${formatMoney(p.price)}</p>
                                <p class="rating">
                                ${p.rating} <span class="text-warning"><i class="fa-solid fa-star"></i></span>
                                </p>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        `;
    }
};

// Format tiền
const formatMoney = number => {
    return number.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
}

renderProduct(products);
