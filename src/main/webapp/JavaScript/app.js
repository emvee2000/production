var sliders = document.getElementsByClassName("slider");
var i;

for (i = 0; i < sliders.length; i++) {
    sliders[i].addEventListener("input", function () {
        this.classList.toggle("active");
        var content = this.previousElementSibling;
        content.innerHTML = this.value;
    });
}

var runs = document.getElementsByClassName("runs");

for (let i = 0; i < runs.length; i++) {
    runs[i].addEventListener("change", function () {
        var runs = this.value;
        var content = document.getElementsByClassName("multiply-" + this.id);
        for (let j = 0; j < content.length; j++) {
            const element = content[j];
            element.innerHTML = runs * element.previousElementSibling.innerHTML;
        }
    });

}