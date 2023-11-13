import { Server } from "socket.io";

export default function SocketHandler(req, res) {

    if (res.socket.server.io) {
        console.log("Already set up");
        res.end();
        return;
    }

    const io = new Server(res.socket.server);

    // Event handler for client connections
    io.on('connection', (socket) => {
        const clientId = socket.id;
        console.log(`A client connected. ID: ${clientId}`);

        // Event handler for receiving messages from the client
        socket.on('message', (data) => {
            console.log('Received message:', data);
        });

        socket.on('new_post', ()=>{
            console.log("New post")
        })

        // Event handler for client disconnections
        socket.on('disconnect', () => {
            console.log('A client disconnected.');
        });
    });

    res.socket.server.io = io;
    res.end();
}