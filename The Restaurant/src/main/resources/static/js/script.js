console.log('Script.js loaded');

function addComment() {
    console.log('Trying to add a comment.');
    var commentText = document.getElementById('commentText').value;

    fetch('http://localhost:8080/api/comments/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Origin': 'http://localhost:8080'
        },
        body: JSON.stringify({ text: commentText }),
        credentials: 'include'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok: ' + response.status + ' ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log('Comment added:', data);

            var commentsList = document.getElementById('commentsList');
            var listItem = document.createElement('li');
            listItem.innerHTML = data.text + ' (by ' + data.userEmail + ') <button onclick="deleteComment(' + data.id + ')">DELETE</button>';
            commentsList.appendChild(listItem);

            document.getElementById('commentText').value = '';

            location.reload();
        })
        .catch(error => console.error('Error:', error));
}

function deleteComment(id) {
    fetch('/api/comments/delete/' + id, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                if (response.status === 403) {
                    alert("Само админ може да трие коментари.");
                    throw new Error('Няма разрешение за триене на коментар.');
                } else {
                    throw new Error('Network response was not ok: ' + response.status + ' ' + response.statusText);
                }
            }
        })
        .then(data => {
            console.log('Comment deleted:', data);
            loadComments();
            location.reload(); // Презареждане на страницата
        })
        .catch(error => console.error('Error:', error));
}

function loadComments() {
    fetch('/api/comments/view')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            var commentsList = document.getElementById('commentsList');
            commentsList.innerHTML = '';

            data.forEach(function (comment) {
                var listItem = document.createElement('li');
                listItem.className = 'comment'; // добавете тази линия
                listItem.innerHTML = comment.text + ' (by ' + comment.userEmail + ') <button onclick="deleteComment(' + comment.id + ')">DELETE</button>';
                commentsList.appendChild(listItem);
            });
        })
        .catch(error => console.error('Error:', error));
}

document.addEventListener('DOMContentLoaded', function () {
    loadComments();
});