$(document).ready(() => {
    $(".sign-up-container").submit((e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const data = {
            username: formData.get("reg-username"),
            name: formData.get("reg-name"),
            surname: formData.get("reg-surname"),
            password: formData.get("reg-password"),
            confirmPassword: formData.get("reg-repeat-password")
        };

        $.ajax({
            url: "authorization/register",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: () => {
                alert("SUCCESS");
            },
            error: () => {
                alert("ERROR");
            }
        });
    });
});

/*
1. auth-pan_step1_out
2. auth-pan_step2_out
3. auth-pan-step3_out
4. hide

1. auth-pan_step1_in
2. auth-pan_step2_in
3. auth-pan-step3_in
4. show
*/

const SIGNIN_IN = "signIn_In";
const SIGNIN_OUT = "signIn_Out";
const SIGNUP_IN = "signUp_In";
const SIGNUP_OUT = "signUp_Out";

const leftButton = document.querySelector(".side-button--left");
const rightButton = document.querySelector(".side-button--right");

const signInPan = document.querySelector(".main__sign-in");
const signUpPan = document.querySelector(".main__sign-up");

rightButton.addEventListener("click", changeSignInPan);
leftButton.addEventListener("click", changeSignUpPan);

function changeSignInPan() {
    rightButton.disabled = true;
    setViewState(rightButton, false);
    play(signUpPan, SIGNUP_OUT);
    signUpPan.addEventListener("animationend", function __one__() {
        signUpPan.removeEventListener("animationend", __one__);
        setViewState(signUpPan, false);
        setViewState(signInPan, true);
        play(signInPan, SIGNIN_IN, () => {
            leftButton.disabled = false;
            setViewState(leftButton, true);
        });
    });
}

function changeSignUpPan() {
    leftButton.disabled = true;
    setViewState(leftButton, false);
    play(signInPan, SIGNIN_OUT);
    signInPan.addEventListener("animationend", function __two__() {
        signInPan.removeEventListener("animationend", __two__);
        setViewState(signInPan, false);
        setViewState(signUpPan, true);
        play(signUpPan, SIGNUP_IN, () => {
            rightButton.disabled = false;
            setViewState(rightButton, true);
        });
    });
}

function play(domObj, name, action = null) {
    domObj.classList.add(name);
    domObj.addEventListener("animationend", function __end__() {
        domObj.removeEventListener("animationend", __end__);
        if (action != null)
            action();

        domObj.style.position = "static";
        domObj.classList.remove(name);
    });
}

function setViewState(domObj, state, showOption = "flex") {
    domObj.style.display = state ? showOption : "none";
}

function changeDefaultState() {
    setViewState(leftButton, false);
    setViewState(signUpPan, true);
    setViewState(signInPan, false);
}

changeDefaultState();