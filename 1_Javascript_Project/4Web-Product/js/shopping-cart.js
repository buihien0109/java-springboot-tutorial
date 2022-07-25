// Truy cập vào các thành phần
const productListEl = document.querySelector(".product-list");
const countEl = document.querySelector(".count");
const discountInputEl = document.getElementById("discount-form-input");
const btnApply = document.getElementById("btn-apply");
const discountBoxEl = document.querySelector(".discount-box");

const totalMoneyEl = document.getElementById("total-money");
const discountMoneyEl = document.getElementById("discount-money");
const subTotalMoneyEl = document.getElementById("sub-total-money");

const shoppingCartEl = document.querySelector(".shopping-cart");
const messageEl = document.querySelector(".message");

// Khai báo biến
let items = getFromLocalStorage("cart");

let discountCode = {
    A : 0.1,
    B : 0.2,
    C : 0.3,
    D : 0.4
};

let code = null;

// Hiển thị danh sách sản phẩm trong giỏ hàng
const renderProducts = (arr) => {
    productListEl.innerHTML = "";

    // TODO : Kiểm tra không có sản phẩm nào trong giỏ --> hiển thị thông tin
    if(arr.length == 0) {
        messageEl.classList.remove("d-none");
        shoppingCartEl.classList.add("d-none");
        return;
    }

    messageEl.classList.add("d-none");
    shoppingCartEl.classList.remove("d-none");

    let html = "";
    arr.forEach(p => {
        html += `
            <div class="product-item d-flex border mb-4">
                <div class="image">
                    <img src="${p.image}" alt="${p.name}" />
                </div>
                <div class="info d-flex flex-column justify-content-between px-4 py-3 flex-grow-1">
                    <div>
                        <div class="d-flex justify-content-between align-items-center">
                            <h2 class="text-dark fs-5 fw-normal">${p.name} (${p.size})</h2>
                            <h2 class="text-danger fs-5 fw-normal">${formatMoney(p.price)}</h2>
                        </div>
                        <p class="text-black-50">
                            <span class="border d-inline-block me-3">
                                <span class="py-2 px-3 d-inline-block fw-bold bg-light" onclick="minusCount(${p.id}, '${p.size}')">-</span>
                                <span class="py-2 px-3 d-inline-block fw-bold count">${p.count}</span>
                                <span class="py-2 px-3 d-inline-block fw-bold bg-light" onclick="plusCount(${p.id}, '${p.size}')">+</span>
                            </span>
                        </p>
                    </div>
                    <div>
                        <button class="text-primary border-0 bg-transparent fw-light" onclick="removeProduct(${p.id}, '${p.size}')">
                            <span><i class="fa-solid fa-trash-can"></i></span>
                            Xóa
                        </button>
                    </div>
                </div>
            </div>
        `
    });

    productListEl.innerHTML = html;

    // Cập nhật tổng tiền
    updateTotalMoney(arr, code);
}

// Tăng số lượng
const plusCount = (id, size) => {
    // Tìm kiếm sản phẩm 
    let product = items.find(p => p.id == id && p.size == size);
    product.count++;

    // Render lại
    renderProducts(items);

    // Lưu lại vào localStorage
    addToLocalStorage(items);
}

// Giảm số lượng
const minusCount = (id, size) => {
    // Tìm kiếm sản phẩm 
    let product = items.find(p => p.id == id && p.size == size);
    product.count--;
    if(product.count == 0) {
        product.count = 1;
    }

    // Render lại
    renderProducts(items);

    // Lưu lại vào localStorage
    addToLocalStorage(items);
}

// Xóa sản phẩm
const removeProduct = (id, size) => {
    let isConfirm = confirm("Bạn có muốn xóa không?");
    if(isConfirm) {
        // Xóa sản phẩm trong mảng ban đầu
        items = items.filter(p => p.id != id || p.size != size);

        // Hiển thị trên giao diện
        renderProducts(items);

        // Lưu lại vào localStorage
        addToLocalStorage(items);
    }
}

// Tính tổng tiền
const getTotalMoney = (arr) => {
    let total = 0;
    arr.forEach(p => {
        total += p.price * p.count;
    });
    return total;
}

// Cập nhật tổng tiền
const updateTotalMoney = (arr, code) => {
    let subTotal = getTotalMoney(arr);
    let discount = 0;

    if(discountCode[code]) {
        discount = subTotal * discountCode[code];
        discountBoxEl.classList.remove("d-none");
    } else {
        discountBoxEl.classList.add("d-none");
    }

    let total = subTotal - discount;

    // Cập nhật giá tiền từng loại
    subTotalMoneyEl.innerText = formatMoney(subTotal)
    discountMoneyEl.innerText = formatMoney(discount);
    totalMoneyEl.innerText = formatMoney(total);


}

// Xử lý phần áp dụng mã giảm giá
btnApply.addEventListener("click", function() {
    // Lấy mã code người dùng nhập vào ô input
    let value = discountInputEl.value;

    // Nếu không tồn tại mã code -> error
    if(!value) {
        alert("Mã giảm giá không được để trống");
        code = null;
        updateTotalMoney(items, code);
        return;
    }

    // Nếu mã code = mã code đã áp dụng
    if(value == code) {
        alert("Mã giảm giá đã được áp dụng");
        return
    }

    // Nếu mã code không hợp lệ
    if(!discountCode[value]) {
        alert("Mã giảm giá không hợp lệ");
        code = null;
        updateTotalMoney(items, code);
        return;
    }

    code = value;
    updateTotalMoney(items, code);
})

renderProducts(items);

