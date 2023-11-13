import React from "react";
import Image from "next/image";
import { girl_2 } from "@/assets/images";
import InputWithIcon from "./InputWithIcon";
import UserInfoSheet from "./UserInfoSheet";
import { ModeToggle } from "./ModeToggle";

const ChatHeader = () => {
  return (
    <div className="sticky bg-white top-0 z-10 border-b border-b-slate-300 flex justify-between items-center w-full p-4">
      <div>
        <div className="flex gap-4 items-center">
          <div className="relative">
            {/* <Image src={girl_2} alt="User avatar" width={40} height={40} /> */}
            <UserInfoSheet />
            <span className="h-2 w-2 rounded-[50%] bg-green-500 absolute right-1 bottom-0 border border-white"></span>
          </div>

          <div className="flex flex-col items-between">
            <div className="text-sm font-semibold">Michael</div>
            <p className="text-xs">Online</p>
          </div>
        </div>
      </div>
      <div className="flex items-center gap-3">
        <form>
          <InputWithIcon
            startIcon={"search"}
            placeholder={"Search in conversation..."}
            size={16}
            classnames={"border-slate-300 border rounded-md"}
          />
        </form>

        {/* <ModeToggle /> */}
      </div>
    </div>
  );
};

export default ChatHeader;
