import {
  Sheet,
  SheetContent,
  SheetDescription,
  SheetFooter,
  SheetHeader,
  SheetClose,
  SheetTrigger,
  SheetTitle,
} from "./ui/sheet";
import { Avatar, AvatarFallback, AvatarImage } from "./ui/avatar";
import { girl_2 } from "@/assets/images";
import Image from "next/image";
import { Phone, Video, Edit2 } from "lucide-react";

const UserInfoSheet = () => {
  return (
    <Sheet>
      <SheetTrigger asChild>
        <Image
          src={girl_2}
          width={40}
          height={40}
          alt="User avatar"
          className="cursor-pointer"
        />
      </SheetTrigger>
      <SheetContent className="w-[300px]">
        <SheetHeader>
          <SheetTitle>Contact Info</SheetTitle>
        </SheetHeader>

        {/* <div className="flex flex-col gap-4 py-4 border-b-slate-400 border-b">
          <div className="flex gap-4">
            <Avatar>
              <AvatarImage src="girl1.png"></AvatarImage>
              <AvatarFallback>CN</AvatarFallback>
            </Avatar>
            <div className="flex flex-col">
              <span className="text-sm text-slate-600 font-semibold">
                John Doe
              </span>
              <span className="text-xs text-slate-500">john@gmail.com</span>
            </div>
          </div>

          <div className="w-full flex justify-around items-center">
            <div className="flex flex-col gap-2 items-center">
              <Phone size={22} />
              <span className="text-sm text-slate-500">Phone</span>
            </div>
            <div className="flex flex-col gap-2 items-center">
              <Video size={22} />
              <span className="text-sm text-slate-500">Video</span>
            </div>
          </div>
        </div> */}

        <div className="flex justify-center py-8">
          <Image
            src={girl_2}
            width={250}
            height={250}
            alt="User profile"
            className="rounded-[50%]"
          />
        </div>

        <div className="flex flex-col gap-1 py-4 border-b border-b-slate-400">
          <span className="text-sm font-semibold">Fullname</span>
          <p className="text-sm">John Doe</p>
        </div>

        <div className="flex flex-col gap-1 py-4 border-b border-b-slate-400">
          <span className="text-sm font-semibold">Email address</span>
          <p className="text-sm">john@gmail.com</p>
        </div>

        <div className="flex flex-col gap-1 py-4 border-b border-b-slate-400">
          <span className="text-sm font-semibold">About</span>
          <p className="text-sm">Trying to escape the matrix</p>
        </div>
      </SheetContent>
    </Sheet>
  );
};

export default UserInfoSheet;
