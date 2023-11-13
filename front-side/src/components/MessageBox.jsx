import React from "react";
import { MoreVertical } from "lucide-react";
import {
  Menubar,
  MenubarContent,
  MenubarItem,
  MenubarMenu,
  MenubarSeparator,
  MenubarTrigger,
} from "./ui/menubar";

const MessageBox = ({ isInitiator, data }) => {
  return (
    <div
      className={`flex items-start gap-3 ${
        isInitiator ? "self-end" : "self-start"
      }`}
    >
      <div
        className={`flex flex-col max-w-[250px] shadow-sm rounded-lg p-2 ${
          isInitiator ? "bg-blue-600 text-white" : "bg-white"
        }`}
      >
        <div className="mb-2">Lorem, ipsum dolor sit.</div>
        {/* <p className="self-end text-xs text-slate-400 font-normal">23:09</p> */}
      </div>
      <Menubar>
        <MenubarMenu>
          <MenubarTrigger>
            <MoreVertical size={15} className="cursor-pointer" />
          </MenubarTrigger>
          <MenubarContent>
            <MenubarItem>Edit</MenubarItem>
            <MenubarSeparator />
            <MenubarItem>Delete</MenubarItem>
          </MenubarContent>
        </MenubarMenu>
      </Menubar>
      {/* <MoreVertical size={15} className="cursor-pointer" /> */}
    </div>
  );
};

export default MessageBox;
