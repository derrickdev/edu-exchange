import { create } from "zustand";
import axiosRequest from "@/utils/axiosRequest";

export const useUserInfoStore = create((set) => ({
  userInfo: {},
  fetchUserInfo: async () => {
    try {
      const response = await axiosRequest.get("/auth/userinfo");
      set({ userInfo: response.data });
    } catch (error) {
      console.error(error);
    }
  },
}));
