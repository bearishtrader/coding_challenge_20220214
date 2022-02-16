// 1) JavaScript:
// Write a function that retrieves the top 3 longest words of a newspaper headline and transforms them into hashtags. If multiple words tie for the same length, retrieve the word that occurs first.
// 
// Examples
// getHashTags("How the Avocado Became the Fruit of the Global Trade")
// --> ["#avocado", "#became", "#global"]
// 
// getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year")
// --> ["#christmas", "#probably", "#will"]
// 
// getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit")
// --> ["#surprise", "#parents", "#fruit"]
// 
// getHashTags("Visualizing Science")
// --> ["#visualizing", "#science"] 
function getHashTags(headline) {
    let words = String(headline).split(/[ ,]+/)
    let uniqueWords = new Set();
    words.forEach(word => uniqueWords.add(word))
    let uniqueWordArray = Array.from(uniqueWords)
    let wordLengthArray = [];
    uniqueWordArray.forEach( word => wordLengthArray.push({len : word.length, str: word}));
    // Sort descending longest word at top, if the length is the same word appearing first in heading goes to top of list (lowest array index)
    wordLengthArray.sort( (b,a) => {
        if (a.len<b.len) {
            return -1;
        } else if (a.len>b.len) {
            return 1;
        }
        if (a.len==b.len) { // If the words are the same length get first word from headline
            if (headline.indexOf(b.str)<headline.indexOf(a.str)) {
                return -1} else {
                    return 1;
                }
        }
    });
    hashTags = []
    for (let i=0; i<3 && i<wordLengthArray.length; i++) {
        hashTags.push("#" + wordLengthArray[i].str.toLowerCase());
    }    
    return hashTags;
}

// To run from bash:
// node question1Solution.js
console.log(getHashTags("How the Avocado Became the Fruit of the Global Trade"))
console.log(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year"))
console.log(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit"))
console.log(getHashTags("Visualizing Science"))