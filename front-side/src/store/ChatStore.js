import { create } from "zustand";

const chatStore = create((set)=>({
    showMessageOption: false,
    messageSelected: null,
    setShowMessageOption: val => set(() => ({showMessageOption: val})),
    setMessageSelected: val => set(() => ({messageSelected: val})),
}))

export default chatStore;