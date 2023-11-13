import ChatHeader from "@/components/ChatHeader";
import ChatSidebar from "@/components/ChatSidebar";
import ChatFooter from "@/components/ChatFooter";
import EmptyMain from "@/components/EmptyMain";
import React from "react";
import MessageBox from "@/components/MessageBox";
import { io } from "socket.io-client";

const Conversations = () => {
  return (
    <div className="w-full flex">
      <ChatSidebar />
      <main className="w-full flex flex-col">
        <ChatHeader />
        <div className="flex flex-col gap-4 p-4 flex-1 bg-slate-100">
          <MessageBox isInitiator />
          <MessageBox isInitiator />
          <MessageBox />
          <MessageBox isInitiator />
          {/* <MessageBox />
          <MessageBox />
          <MessageBox />
          <MessageBox isInitiator />
          <MessageBox />
          <MessageBox /> */}
        </div>
        {/* <EmptyMain /> */}
        <ChatFooter />
      </main>
    </div>
  );
};

export default Conversations;
