import CryptoJS from 'crypto-js'

export const Sign = (message, route) => {
    let data = buildData(message, route);

    let secret = document.getElementById('shared_secret').value;
    let hash = CryptoJS.HmacSHA256(data, secret);
    return CryptoJS.enc.Base64.stringify(hash);
}

const buildData = (message, route) => {
    let reply = '';

    for (const [key, value] of Object.entries(message)) {
        reply += key + ':';
        reply += Array.isArray(value) ? '[' : '';
        reply += value;
        reply += Array.isArray(value) ? ']' : '';
        reply += ';';
    }

    reply += 'x-Route:' + route + ';'
    reply = reply.split(' ').join('')
    console.log(reply)

    return reply;
}