window.onload = function() {
    var cat = document.querySelector('.category');
    dropdownlist(cat.options[cat.selectedIndex].value);
};

//onchange="dropdownlist(this.options[this.selectedIndex].value);"
//                <h:outputScript target="body" library="js" name="subcategory.js" />
function dropdownlist(listindex)
{
    var sub = document.querySelector('.subcategory');
    sub.style.display = "none";
    switch (listindex)
    {
        case "1" :
            sub.innerHTML = "";
            sub.style.display = "none";
            break;
        case "2" :
            sub.style.display = "block";
            sub.innerHTML = "";
            sub.innerHTML += "<option value='1'>Buty</option>";
            sub.innerHTML += "<option value='1'>Majty</option>";
            sub.innerHTML += "<option value='1'>Kurtki</option>";
            break;

        case "3" :
            sub.style.display = "block";
            sub.innerHTML = "";
            sub.innerHTML += "<option value='1'>Komputery</option>";
            sub.innerHTML += "<option value='1'>RTV i AGD</option>";
            sub.innerHTML += "<option value='1'>Telefony</option>";
            break;

        case "4" :
            sub.style.display = "block";
            sub.innerHTML = "";
            sub.innerHTML += "<option value='1'>Dildo</option>";
            sub.innerHTML += "<option value='1'>Specyfiki</option>";
            sub.innerHTML += "<option value='1'>Pornosy</option>";
            break;

        default:
            sub.innerHTML = "";
            sub.style.display = "none";
            break;
    }
    return true;
}