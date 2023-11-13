import React, { useEffect, useState } from "react";
import { ChatCard } from "./ChatCard";
import { MessageCircle, Phone, Users2, Sheet } from "lucide-react";
import { dm_sans, montserrat } from "@/utils/fonts";
import InputWithIcon from "./InputWithIcon";
import { ModeToggle } from "./ModeToggle";
import { Avatar, AvatarFallback, AvatarImage } from "./ui/avatar";
import { girl_1 } from "@/assets/images";
import Image from "next/image";
import UserInfoSheet from "./UserInfoSheet";
import Link from "next/link";
import axiosRequest from "@/utils/axiosRequest";
import { UsersList } from "./UsersList";

const user = {
  fullname: "Michael B",
  message: "Sent you a file",
};

const ChatSidebar = ({ props }) => {

  const [conversations, setConversations] = useState(null)
  const [users, setUsers] = useState(null)

  useEffect(()=>{
    axiosRequest.get("/admin/users").then(res => {
      setUsers(res.data)
    }).catch(err => console.log(err))
  }, [])

  useEffect(()=>{
    axiosRequest.get("/conversations").then(res => {
      console.log(res)
    }).catch(err => console.log(err))
  }, [])


  return (
    <div className="flex w-1/3 sticky top-0 bottom-0 h-screen overflow-scroll no-scrollbar overflow-x-hidden border-r-slate-300 border-r">
      <div className="flex flex-col justify-between px-2 py-4 gap-2 bg-slate-200 border-r border-slate-300 sticky bottom-0 top-0 h-full">
        <div className="flex flex-col gap-8 items-center">
          <UserInfoSheet />

          <div className="flex flex-col gap-3 items-center">
            <div className="bg-blue-600 rounded-lg flex justify-center items-center p-3 cursor-pointer hover:bg-blue-600">
              <MessageCircle size={18} />
            </div>
            {/* <div className="rounded-lg flex justify-center items-center p-3 cursor-pointer hover:bg-blue-600">
              <Users2 size={18} />
            </div> */}
            <div className="rounded-lg flex justify-center items-center p-3 cursor-pointer hover:bg-blue-600">
              <Link href="/posts">
                <Sheet size={18} />
              </Link>
            </div>
          </div>
        </div>
        {/* <div>
          <ModeToggle />
        </div> */}
      </div>

      <div className="flex flex-col gap-2 w-full">
        {/* <div className="flex justify-between items-center w-full">
          <h2 className={`${dm_sans.className} text-xl font-semibold`}>
            Chats
          </h2>
        </div> */}

        <div className="p-4">
          <InputWithIcon
            startIcon={"search"}
            placeholder={"Search..."}
            size={16}
            classnames={
              "border-slate-300 rounded-lg bg-slate-200 dark:bg-transparent dark:border"
            }
          />
          {/* <UsersList users={users} /> */}
        </div>

        <div className="flex flex-col py-4 gap-2">
          <ChatCard props={user} />
        </div>
      </div>
    </div>
  );
};

export default ChatSidebar;
