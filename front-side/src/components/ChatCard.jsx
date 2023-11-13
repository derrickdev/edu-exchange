import React, {useState} from "react";
import { girl_1 } from "@/assets/images";
import { dm_sans, montserrat } from "@/utils/fonts";
import { Avatar, AvatarFallback, AvatarImage } from "./ui/avatar";

export function ChatCard({ props }) {
  const { fullname, id } = props;
  const [selectedChatId, setSelectedChatId] = useState(null)
  return (
    <div
      className={`flex justify-between items-center w-full p-4 cursor-pointer rounded-sm hover:bg-slate-100 ${dm_sans.className} ${selectedChatId === id ? "bg-slate-200" : "bg-white"}`}
      onClick={()=> setSelectedChatId(id)}
    >
      <div className="flex gap-4 items-center">
        <Avatar>
          <AvatarImage src='girl1.png'></AvatarImage>
          <AvatarFallback>CN</AvatarFallback>
        </Avatar>
        <div className="flex flex-col">
          <span className={`text-sm font-semibold`}>{fullname}</span>
          <span className="text-sm text-slate-500 truncate w-24">Click to send message</span>
        </div>
      </div>
      {/* <div className="flex flex-col items-end gap-2">
        <span className="flex justify-center text-xs text-slate-400 font-semibold">
          22:00
        </span>
        <span className="flex justify-center items-center h-5 w-5 bg-blue-600 rounded-[50%] text-xs text-white">
          3
        </span>
      </div> */}
    </div>
  );
}
