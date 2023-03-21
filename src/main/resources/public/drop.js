function dropHandler(ev) {
    console.log("File(s) dropped");

    // Prevent default behavior (Prevent file from being opened)
    ev.preventDefault();

    if (ev.dataTransfer.items) {
        // Use DataTransferItemList interface to access the file(s)
        [...ev.dataTransfer.items].forEach((item, i) => {
            // If dropped items aren't files, reject them
            if (item.kind === "file") {
                const file = item.getAsFile();

                upload(file).then(r => console.log(r));

                console.log(`… file[${i}].name = ${file.name}`);

                let filename = file.name;
                let size = file.size;
                let html = '<p>File: ' + filename + '<br>';
                html += 'Size: ' + fileSize(size) + '<br>';
                html += 'Last Modified: ' + file.lastModifiedDate + '</p>';
                document.getElementById("drop_zone").innerHTML = html;
            }
        });
    } else {
        // Use DataTransfer interface to access the file(s)
        [...ev.dataTransfer.files].forEach((file, i) => {
            upload(file).then(r => console.log(r));
            console.log(`… file[${i}].name = ${file.name}`);
        });
    }
}

async function upload(file) {
    console.log("Uploading file: " + file.name);
    const response = await fetch('/upload', {
        method: 'POST',
        body: file
    });
    const data = await response.json();
    console.log(data);
}

function fileSize(size) {
    if (size < 1024) {
        return size + " bytes";
    } else if (size >= 1024 && size < 1048576) {
        return (size / 1024).toFixed(1) + " KB";
    } else if (size >= 1048576) {
        return (size / 1048576).toFixed(1) + " MB";
    }
}