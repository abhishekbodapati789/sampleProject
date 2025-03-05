document.addEventListener("DOMContentLoaded", function () {
    console.log("JavaScript Loaded!"); // Debugging log

    var modal = document.getElementById("videoModal");
    var embeddedVideo = document.getElementById("embeddedVideo");
    var modalVideo = document.getElementById("videoPlayer");
    var modalSource = document.getElementById("videoSource");
    var closeBtn = document.querySelector(".close");

    if (!embeddedVideo) {
        console.error("Embedded video element not found!");
        return;
    }

    var isYouTube = "${bot.isYouTubeVideo}" === "true";
    var videoUrl = "${bot.embeddedYouTubeUrl}";
    var damUrl = "${bot.videoUrl}";

    // Attach click event to the embedded video
    embeddedVideo.addEventListener("click", function (event) {
        console.log("Embedded video clicked!", event);

        // Prevent default behavior
        event.preventDefault();
        event.stopPropagation();

        // Show modal
        modal.style.display = "flex";

        if (isYouTube) {
            modalVideo.style.display = "none";
            document.getElementById("videoPlayer").src = videoUrl;
            document.getElementById("videoPlayer").style.display = "block";
        } else {
            document.getElementById("videoPlayer").style.display = "none";
            modalSource.src = damUrl;
            modalVideo.load();
            modalVideo.style.display = "block";
        }
    });

    // Close modal when clicking close button
    closeBtn.addEventListener("click", function () {
        closeModal();
    });

    function closeModal() {
        modal.style.display = "none";
        document.getElementById("videoPlayer").src = "";
        modalVideo.pause();
        modalSource.src = "";
    }
});
