const moreBtn = document.querySelector(".more-btn");
const navigationDrawer = document.querySelector(".navigation-drawer-panel");

moreBtn.addEventListener("click", () => {
    moreBtn.classList.toggle("open");
    navigationDrawer.classList.toggle("open");
});