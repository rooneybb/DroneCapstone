import express = require("express");


/// the index just returns hello world
export function index(req: express.Request, res: express.Response) {
    res.send('Hello World!');
};