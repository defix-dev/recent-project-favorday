import React from "react";
import {TagConverter} from "../../js/custom_tags_lib";

export function PostContent({ contentData }) {
    const convertedText = `<p>${TagConverter.convert(contentData, true)}</p>`;

    return (<div>
        <div dangerouslySetInnerHTML={{__html: convertedText}}></div>
    </div>)
}

