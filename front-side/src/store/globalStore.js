import { create } from "zustand";

const globalStore = create((set) => ({
  shouldRefetchData: false,
  searchQuery: "",
  setSearchQuery: (val) => set(() => ({ searchQuery: val })),
  setShouldRefetchData: (val) => set(() => ({ shouldRefetchData: val })),
}));

export default globalStore;
