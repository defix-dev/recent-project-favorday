class ButtonDropdownExecutor {
    _dropdown;
    _buttonDOMObject;

    constructor(dropdown, buttonDOMObject) {
        this._dropdown = dropdown;
        this._buttonDOMObject = buttonDOMObject;
        this.init();
    }

    init() {
        this._buttonDOMObject.addEventListener("click", () => {
            this._dropdown.show();
        });

        document.addEventListener("mousedown", (event) => {
           this._dropdown.tryHide(event);
        });
    }
}

class Dropdown {
    _dropdownDOMObject;
    _dropdownHeaderTextDOMObject;

    constructor(isActivated = false,
                dropdownDOMObject,
                dropdownHeaderTextDOMObject) {
        this.isActivated = isActivated;
        this._dropdownDOMObject = dropdownDOMObject;
        this._dropdownHeaderTextDOMObject = dropdownHeaderTextDOMObject;
        this.init();
    }

    init() {
        new ButtonDropdownExecutor(this, this._dropdownHeaderTextDOMObject);

        if(!this.isActivated)
            this.hide();
        else
            this.show();
    }

    resize() {
        const header = document.querySelector("header").getBoundingClientRect();
        const dropdownRect = this._dropdownDOMObject.getBoundingClientRect();
        const textRect = this._dropdownHeaderTextDOMObject.getBoundingClientRect();
        this._dropdownDOMObject.style.left = (textRect.left - (Math.abs(dropdownRect.width-textRect.width)/2)) + "px";
        this._dropdownDOMObject.style.top = (header.top + header.height*1.1) + "px";
    }

    show() {
        blurBg();
        this._dropdownDOMObject.style.display = "block";
        this.isActivated = true;
        this.resize();
    }

    hide() {
        unBlurBg();
        this._dropdownDOMObject.style.display = "none";
        this.isActivated = false;
    }

    tryHide(event) {
        const dropdownRect = this._dropdownDOMObject.getBoundingClientRect();
        if(event.clientX > dropdownRect.right
        || event.clientX < dropdownRect.left
        || event.clientY < dropdownRect.top
        || event.clientY > dropdownRect.bottom) {
            this.hide();
        }
    }
}

function blurBg(force = 10) {
    document.querySelector(".aside").style.filter = `blur(${force}px)`;
    document.querySelector(".main").style.filter = `blur(${force}px)`;
}

function unBlurBg() {
    document.querySelector(".aside").style.filter = `none`;
    document.querySelector(".main").style.filter = `none`;
}

const dropdowns = [];
dropdowns.push(new Dropdown(
    false,
    document.querySelector(".header__language-select-pan"),
    document.querySelector(".header__language-select-button")
));

function resizeDropdown() {
    for(const dropdown of dropdowns) {
        dropdown.resize();
    }
}

window.addEventListener("resize", resizeDropdown);

setupLangButton("ru", "ru");
setupLangButton("en", "en");
setupLangButton("jpn", "jpn");

function setupLangButton(buttonId, language) {
    document.querySelector(`#${buttonId}`).addEventListener("click", async () => {
        await changeLanguage(language);
        location.reload();
    });
}

async function changeLanguage(language) {
    await fetch(`/localization/configuration/set_language?lang=${language}`, {
        method: "POST"
    });
}
