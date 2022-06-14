// Truy cập vào các thành phần
const breadcrumbItemActiveEl = document.querySelector(".breadcrumb-item.active");
const carouselEl = document.getElementById("carousel");
const detailEl = document.getElementById("detail");

// Lấy thông tin id trên URL
let params = new URLSearchParams(window.location.search);
let id = params.get("id");

// Tạo biến để lưu thông tin của product
let product;

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
    detailEl.innerHTML = `
        <h2 class="fs-3">${product.name}</h2>
        <p class="text-danger fs-4 fw-bold mb-4">${formatMoney(product.price)}</p>

        <div class="sizes mb-4">
            ${product.sizes.map(size => {
                return `<span class="border py-2 px-3 border-dark me-2">${size}</span>`
            }).join("")}
        </div>

        <div class="d-flex align-items-center mb-4">
            <span class="border d-inline-block me-3">
                <span class="py-2 px-3 d-inline-block fw-bold bg-light">-</span>
                <span class="py-2 px-3 d-inline-block fw-bold">1</span>
                <span class="py-2 px-3 d-inline-block fw-bold bg-light">+</span>
            </span>

            <button class="btn btn-dark py-2 rounded-0">Thêm vào giỏ hàng</button>
        </div>

        <div class="product-info">${product.description}</div>
    `

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

// Format tiền
const formatMoney = number => {
    return number.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
}

renderProduct(product)


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