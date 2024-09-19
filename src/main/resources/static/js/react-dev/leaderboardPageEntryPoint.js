import React from 'react';
import ReactDOM from 'react-dom';
import {createRoot} from "react-dom/client";

document.addEventListener('DOMContentLoaded', async () => {
    const response = await fetch('/api/leaderboard/users_data');

    if (!response.ok)
        return;

    const data = await response.json();

    const modifiedData = [];
    for (let i = 0; i < data.length; i++) {
        modifiedData.push({
            ratingId: i + 1,
            author: data[i].author,
            voiceCount: data[i].voiceCount,
            postId: data[i].postId
        });
    }

    console.log(modifiedData);
});