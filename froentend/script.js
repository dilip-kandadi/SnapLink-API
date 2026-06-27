const urlInput = document.getElementById('url');
const shortBtn = document.getElementById('short');
const resultDisplay = document.getElementById('result');
const codeInput = document.getElementById('shortcode');
const statsBtn = document.getElementById('statsBtn');
const statsResult = document.getElementById('statsResult');

statsBtn.addEventListener('click',async function(){
    const shorturl = codeInput.value;
    if(!shorturl){
        statsResult.innerText="Please Enter ShortUrl...";
        return;
    }
    try{
        const response = await fetch(`http://localhost:8080/stats/${shorturl}`);

        if(response.ok){
            const count = await response.text();
            statsResult.innerHTML=`Total Clicks:    <strong>${count}</strong>`;
        }else{
            statsResult.innerText="Could not find stats for that link.";
        }
    }catch(error){
        console.error("Network Error:", error);
        statsResult.innerText = "Cannot connect to the backend.";
    }
})

shortBtn.addEventListener('click',async function(){

    const longurl = urlInput.value;

    if(!longurl){
        resultDisplay.innerText="Please enter a URL first!";
        return;
    }

    resultDisplay.innerText="Shortening... please wait...";

    try{
        const response = await fetch('http://localhost:8080/api/shorten',{
            method: 'POST',
            headers : {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify({
                  longUrl : longurl
            })
        });

        if(response.ok){
            const data = await response.text();

            console.log("Response from backend:",data);
            resultDisplay.innerHTML=`Shorten URL: <a href="http://localhost:8080/${data}" target="_blank">http://localhost:8080/${data}</a>`
        }else{
           resultDisplay.innerText = "Server error. Could not shorten URL.";
        
        }
    }catch(error){
        console.error("Network Error:", error);
        resultDisplay.innerText = "Cannot connect to the server. Is it running?";
    }
});