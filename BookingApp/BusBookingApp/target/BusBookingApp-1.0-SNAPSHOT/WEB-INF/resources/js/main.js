function deleteApi(path) {
    if(confirm("Bạn có chắc muốn xóa?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if(res.status === 204)
                location.reload();
            else
                alert("Something wrong!!! Try again later.");
        });
    }
}

function lockOrUnlockUser(path) {
    if(confirm("Bạn có chắc muốn thay đổi?") === true) {
        fetch(path, {
            method: "put"
        }).then(res => {
            if(res.status === 204)
                location.reload();
            else
                alert("Something wrong!!! Try again later.");
        });
    }
}


    function submitValidateRoute(event) {
        var des = document.getElementById("destination").value;
        var ori = document.getElementById("origin").value;
            event.preventDefault();
        if(ori === des) {
            document.querySelectorAll(".des_ori_same").forEach(c =>{
                c.contentText = "Nơi đi và Nơi đến không được trùng nhau!!!";
            });
        }
    }
    document.querySelector("button").onClick = submitValidateRoute;


