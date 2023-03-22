(() => {
    const refs = {
        openModalBtn: document.querySelector("[data-modal-open-user]"),
        closeModalBtn: document.querySelector("[data-modal-close-user]"),
        modal: document.querySelector("[data-modal-user]"),
    };

    refs.openModalBtn.addEventListener("click", toggleModal);
    refs.closeModalBtn.addEventListener("click", toggleModal);

    function toggleModal() {
        refs.modal.classList.toggle("is-hidden");
    }
})();