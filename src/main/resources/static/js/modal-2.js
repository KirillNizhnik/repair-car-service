
(() => {
    const refs = {
        openModalBtn: document.querySelector("[data-modal-open-serv]"),
        closeModalBtn: document.querySelector("[data-modal-close-serv]"),
        modal: document.querySelector("[data-modal-serv]"),
    };

    refs.openModalBtn.addEventListener("click", toggleModal);
    refs.closeModalBtn.addEventListener("click", toggleModal);

    function toggleModal() {
        refs.modal.classList.toggle("is-hidden");
    }
})();