$(async function() {
    await thisUser();
});
async function thisUser() {
    fetch("http://localhost:8080/api/user")
        .then(res => res.json())
        .then(data => {
            $('#headerUsername').append(data.username);
            let roleList = data.roleList.map(role => role.role.substring(5).concat(" ")).toString().replaceAll(",", "");
            $('#headerRoles').append(roleList);

            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.name}</td>
                <td>${data.age}</td>
                <td>${data.email}</td>
                <td>${roleList}</td>)`;
            $('#userPanelBody').append(user);
        })
}