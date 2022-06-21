// Truy cập vào các thành phần
const breadcrumbItemActiveEl = document.querySelector(".breadcrumb-item.active");
const carouselEl = document.getElementById("carousel");
const productNameEl = document.querySelector(".product-name");
const productPriceEl = document.querySelector(".product-price");
const productSizeEl = document.querySelector(".product-size");
const productDescriptionEl = document.querySelector(".product-description");

const btnPlusCount = document.querySelector(".btn-plus-count");
const btnMinusCount = document.querySelector(".btn-minus-count");
const btnAddToCart = document.querySelector(".btn-add-to-cart");
const countEl = document.querySelector(".count");

// Lấy thông tin id trên URL
let params = new URLSearchParams(window.location.search);
let id = params.get("id");

// Tạo biến để lưu thông tin của product
let product;
let count = 1;

// Kiểm tra xem có tồn tại id trên url hay không?
if(id) {
    product = products.find(c => c.id == id);

    if(!product) {
        window.location.href = "./404.html";
    }

    document.title = product.name;
    breadcrumbItemActiveEl.innerText = product.name;
} else {
    window.location.href = "./404.html"
}

// Hiển thị thông tin sản phẩm
const renderProduct = product => {
    productNameEl.innerText = product.name;
    productPriceEl.innerText = formatMoney(product.price);
    productDescriptionEl.innerText = product.description;

    // Hiển thị size
    productSizeEl.innerHTML = product.sizes.map(size => {
        return `<span class="border py-2 px-3 border-dark me-2" onclick="choseSize(this)">${size}</span>`
    }).join("");
    
    // Hiển thị hình ảnh
    renderImageProduct(product.images);
}

// Hiển thị hình ảnh
const renderImageProduct = arr => {
    carouselEl.innerHTML = `
        <div id="mainCarousel" class="carousel">
            ${arr.map(ele => {
                return `
                    <div class="carousel__slide" data-src="${ele}" data-fancybox="gallery">
                        <img src="${ele}" />
                    </div>
                `
            }).join("")}
        </div>

        <div id="thumbCarousel" class="carousel max-w-xl mx-auto">
            ${arr.map(ele => {
                return `
                    <div class="carousel__slide">
                        <img class="panzoom__content" src="${ele}" />
                    </div>
                `
            }).join("")}
        </div>
    `
}

// Chọn size
const choseSize = (ele) => {
    let sizeEls = productSizeEl.querySelectorAll("span");
    Array.from(sizeEls).map(el => el.classList.remove("selected", "bg-dark", "text-white"));

    ele.classList.add("selected", "bg-dark", "text-white");
}

// Tăng số lượng
btnPlusCount.addEventListener("click", () => {
    count++;
    countEl.innerText = count;
})

// Giảm số lượng
btnMinusCount.addEventListener("click", () => {
    count--;
    if(count < 1) {
        count = 1;
    }
    countEl.innerText = count;
})

// Thêm vào giỏ hàng
btnAddToCart.addEventListener("click", function() {
    // Kiểm tra xem đã chọn size hay chưa
    let sizeSelectedEl = document.querySelector(".selected");
    if(!sizeSelectedEl) {
        alert("Vùi lòng chọn size trước khi thêm vào giỏ hàng");
        return;
    }

    // Tạo thông tin item để thêm vào giỏ hàng
    let item = {
        id : product.id,
        name : product.name,
        price : product.price,
        image : product.images[0],
        size : sizeSelectedEl.innerText,
        count : count
    }

    // Thêm vào giỏ hàng
    addItemToCart(item);
    alert("Thêm vào giỏ hàng thành công");
})

renderProduct(product);

// Initialise Carousel ********************************
const mainCarousel = new Carousel(document.querySelector("#mainCarousel"), {
    Dots: false,
});

// Thumbnails
const thumbCarousel = new Carousel(document.querySelector("#thumbCarousel"), {
    Sync: {
        target: mainCarousel,
        friction: 0,
    },
    Dots: false,
    Navigation: false,
    center: true,
    slidesPerPage: 1,
    infinite: false,
});

// Customize Fancybox
Fancybox.bind('[data-fancybox="gallery"]', {
    Carousel: {
        on: {
            change: (that) => {
                mainCarousel.slideTo(mainCarousel.findPageForSlide(that.page), {
                    friction: 0,
                });
            },
        },
    },
});