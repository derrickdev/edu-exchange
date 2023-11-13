import React, {useEffect} from "react";
import { useRouter } from "next/navigation";
import { Toaster } from "./ui/toaster";
import { useUserInfoStore } from "@/store/userInfoStore";
import LoadingEffect from "./LoadingEffect";

const Layout = ({ children }) => {

  const router = useRouter();
  const fetchUserInfo = useUserInfoStore((state)=> state.fetchUserInfo)
  const userInfo = useUserInfoStore((state)=> state.userInfo)

  useEffect(() => {
    const token = localStorage.getItem("edu_exchange_access_token");
    if (!token) {
      router.push("/login");
      return
    } else {
      fetchUserInfo()
      // router.push("/posts");
    }
  }, []);


  return (
    <div>
      {children}
      <Toaster />
    </div>
  );
};

export default Layout;
