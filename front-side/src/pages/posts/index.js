import React, { useEffect, useRef, useState } from "react";
import PostCard from "@/components/PostCard";
import PostPageNavbar from "@/components/PostPageNavbar";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Image, Link } from "lucide-react";
import CreatePostCard from "@/components/CreatePostCard";
import CreatePostForm from "@/components/CreatePostForm";
import { Modal } from "@/components/Modal";
import { Button } from "@/components/ui/button";
import Icon from "@/components/Icon";
import PostRuleCard from "@/components/PostRuleCard";
import FilterByTopicCard from "@/components/FilterByTopicCard";
import TeachersListCard from "@/components/TeachersListCard";
import axiosRequest from "@/utils/axiosRequest";
import { useToast } from "@/components/ui/use-toast";
import globalStore from "@/store/globalStore";

const index = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [isModalOpen, setIsModalOPen] = useState(false);
  const shouldRefetchData = globalStore((state) => state.shouldRefetchData);
  const setShouldRefetchData = globalStore(
    (state) => state.setShouldRefetchData
  );
  const [page, setPage] = useState(0);
  const [posts, setPosts] = useState([]);
  const [totalPages, setTotalPages] = useState(null);
  const itemsPerPage = 10;

  const fileUploadInputRef = useRef(null);

  const { toast } = useToast();

  const closeModal = () => setIsModalOPen(false);

  const handleFileUpload = () => {
    if (fileUploadInputRef.current) {
      fileUploadInputRef.current.click();
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = () => {
    setIsLoading(true);
    axiosRequest
      .get(`/posts?page=${page}&size=${itemsPerPage}`)
      .then((res) => {
        if (res.status === 200) {
          const { content, totalPages } = res.data;
          setPosts((prev) => [...prev, ...content]);
          setTotalPages(totalPages);
          if (page < totalPages) {
            setPage((prev) => prev + 1);
          }
        }
      })
      .catch((err) =>
        toast({
          title: "OOps",
          description: err.message,
          variant: "destructive",
        })
      )
      .finally(() => {
        setIsLoading(false);
        setShouldRefetchData(false);
      });
  };

  const fetchByFilter = (filter) => {
    axiosRequest
      .get(`/topics/${filter}/posts`)
      .then((res) => setPosts(res?.data?.content))
      .catch((err) => {
        toast({
          title: "Opps",
          variant: "destructive",
          description: err.message,
        });
      });
  };

  const handleScroll = () => {
    if (
      window.innerHeight + document.documentElement.scrollTop >=
        document.documentElement.offsetHeight - 200 &&
      !isLoading &&
      page < totalPages
    ) {
      fetchData();
    }
  };

  useEffect(() => {
    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, [isLoading]);

  useEffect(() => {
    if (!shouldRefetchData) {
      return;
    }
    setPage(0)
    fetchData();
  }, [shouldRefetchData]);

  return (
    <div className="bg-slate-200 min-h-screen pb-10">
      <PostPageNavbar setPosts={setPosts} />
      <main className="px-8 sm:px-40 flex gap-8">
        <div className="flex flex-col flex-wrap gap-4 mt-4 w-2/3">
          <div className="flex items-center gap-2 p-2 bg-white rounded-sm border border-slate-300">
            <Avatar>
              <AvatarImage src="girl1.png" width={40}></AvatarImage>
              <AvatarFallback>CN</AvatarFallback>
            </Avatar>
            <input type="file" className="hidden" ref={fileUploadInputRef} />
            <div className="flex-1">
              <Modal
                title={"Create Post"}
                isOpen={isModalOpen}
                onclose={() => setIsModalOPen((prev) => !prev)}
                triggerButton={
                  <div>
                    <Button
                      size="icon"
                      className="flex justify-start items-center rounded-sm w-full text-slate-400 px-4"
                      onClick={() => setIsModalOPen(true)}
                      variant="outline"
                    >
                      Create Post
                    </Button>
                  </div>
                }
                description="Create a new post"
                body={<CreatePostForm closeModal={closeModal} />}
                footer={""}
              />
            </div>
            {/* <input
              type="text"
              placeholder="Create Post"
              className="border border-slate-300 bg-slate-100 rounded-sm w-full p-2 focus:outline-none"
            /> */}
            <div className="flex gap-2 text-slate-400">
              <Image
                size={20}
                className="cursor-pointer"
                onClick={handleFileUpload}
              />
              {/* <Link size={20} className="cursor-pointer" /> */}
            </div>
          </div>

          {posts?.length < 1 && <p>Oops no posts found</p>}

          {posts?.map((e, i) => {
            return <PostCard key={i} data={e} />;
          })}
        </div>

        <div className="mt-4 flex flex-col flex-1 gap-4">
          <FilterByTopicCard handleFilter={fetchByFilter} />
          <PostRuleCard />
          <CreatePostCard />
          {/* <TeachersListCard /> */}
        </div>
      </main>
    </div>
  );
};

export default index;
