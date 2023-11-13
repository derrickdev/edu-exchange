import { useState } from "react";
import { Button } from "./ui/button";
import { ExternalLink } from "lucide-react";
import Link from "next/link";
import { Modal } from "./Modal";
import CreatePostForm from "./CreatePostForm";

const CreatePostCard = () => {
  const [isModalOpen, setIsModalOPen] = useState(false);
  const closeModal = () => setIsModalOPen(false);

  return (
    <div className="flex flex-col gap-3 w-full bg-white rounded-sm border border-slate-300">
      <div className="w-full p-3 bg-slate-900 text-sm text-white font-semibold flex items-center rounded-t-sm">
        Have an inquiry?
      </div>

      <div className="p-4 flex flex-col gap-3">
        <p className="text-sm text-slate-500">
          Make sure you read about our community rules before creating posts.
        </p>

        <Modal
          title={"Create Post"}
          isOpen={isModalOpen}
          onclose={() => setIsModalOPen((prev) => !prev)}
          triggerButton={
            <div>
              <Button
                size="icon"
                className="w-full rounded-full"
                onClick={() => setIsModalOPen(true)}
              >
                Create Post
              </Button>
            </div>
          }
          description="Create a new post"
          body={<CreatePostForm closeModal={closeModal} />}
          footer={""}
        />

        {/* <Button className="w-full rounded-full">Create Post</Button> */}
        <Button className="w-full rounded-full border-2" variant="outline">
          <Link href="/chat" className="flex items-center justify-center gap-1">
            <span>Go to conversations </span>
            <ExternalLink size={16} />
          </Link>
        </Button>
      </div>
    </div>
  );
};

export default CreatePostCard;
