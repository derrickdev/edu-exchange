import InputWithIcon from "./InputWithIcon";
import { Plus, MessagesSquare, MessageCircle } from "lucide-react";
import { Avatar, AvatarFallback, AvatarImage } from "./ui/avatar";
import { Button } from "./ui/button";
import Link from "next/link";
import { useUserInfoStore } from "@/store/userInfoStore";
import axiosRequest from "@/utils/axiosRequest";
import { useEffect, useState } from "react";
import { useToast } from "./ui/use-toast";
import Image from "next/image";

const PostPageNavbar = ({setPosts, disabledSearch}) => {
  const {toast} = useToast()
  const userinfo = useUserInfoStore((state)=> state.userInfo)
  const [isSearching, setIsSearching] = useState(false)
  const [searchValue, setSearchValue] = useState("")

  useEffect(()=>{
    if(!isSearching){
      return
    }

    axiosRequest.get(`/posts/search?q=${searchValue}`).then(res => {
      console.log(res)
      setPosts(res.data)
    }).catch(err => {
      toast({
        title: "Opps",
        variant: "destructive",
        description: err.message
      })
    }).finally(()=> setIsSearching(false))
  }, [isSearching, searchValue])

  
  return (
    <div className="flex justify-between items-center sticky top-0 z-10 px-4 py-2 bg-white">
      <div className="flex">
        <Image src="/logo.png" width={100} height={60} />
      </div>
      <InputWithIcon
        startIcon={"search"}
        placeholder={"Search"}
        size={16}
        classnames={"border-slate-300 border rounded-md w-[400px] bg-slate-100"}
        onChange={(e)=>{
          setSearchValue(e.target.value)
          setIsSearching(true)
        }}
        disabled={disabledSearch}
      />
      <div className="flex items-center gap-6">
        {/* <MessagesSquare size={22} className="text-slate-500 cursor-pointer" />
        <MessagesSquare size={22} className="text-slate-500 cursor-pointer" /> */}
        {/* <Plus size={22} className="text-slate-500 cursor-pointer" /> */}

        <Button
          variant="outline"
          className="bg-slate-200 flex items-center gap-1 text-slate-500 text-xs rounded-full"
        >
          <MessageCircle size={15} />
          <Link href="/chat">Conversations</Link>
        </Button>
        <div className="flex gap-2 items-center">
          <Avatar>
            <AvatarImage src="girl1.png" width={40}></AvatarImage>
            <AvatarFallback>CN</AvatarFallback>
          </Avatar>

          <div className="flex flex-col">
            <p className="text-xs font-semibold">{userinfo?.fullname}</p>
            <p className="text-xs text-slate-500">{userinfo?.pseudo}</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PostPageNavbar;
