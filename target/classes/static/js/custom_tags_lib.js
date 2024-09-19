const customTag = (tagName, htmlClassName) => {
    return {
        tag: tagName,
        className: htmlClassName
    }
}

//BASE TAGS
const paragraphTag = customTag("spoiler", "spoiler");

//CONTAINER
export const tagContainer = [];
tagContainer.push(paragraphTag);

export class TagConverter {
    static convert(text, secure = false) {
        let outputText = secure ? TagSecurity.secure(text) : text;

        for(const idx in tagContainer) {
            outputText = outputText.replace(new RegExp(`\\[${tagContainer[idx].tag}\\](.*?)\\[\\/${tagContainer[idx].tag}\\]`, 'ig'),
                `<span class="${tagContainer[idx].className}">$1</span>`);
        }

        return outputText;
    }
}

class TagSecurity {
    static secure(text) {
        return text.replace(/(<(.*?)>)|(<\/(.*?)>)/ig, "");
    }
}
