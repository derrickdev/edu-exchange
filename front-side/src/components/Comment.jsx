import {
  ArrowBigDown,
  ArrowBigUp,
  MessageSquare,
  Flag,
  ThumbsDown,
  ThumbsUp,
} from "lucide-react";
import Image from "next/image";
import { girl_1 } from "@/assets/images";
import UserInfoSheet from "./UserInfoSheet";
import axiosRequest from "@/utils/axiosRequest";

const Comment = ({ data }) => {
  const handleUpvote = () => {
    axiosRequest
      .post(`/votes/${data.id}`, { status: true })
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
  };

  const handleDownVote = () => {
    axiosRequest
      .post(`/votes/${data.id}`, { status: false })
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
  };

  return (
    <div className="flex flex-col gap-3 w-2/3">
      <div className="flex flex-col gap-2 py-2">
        <div className="flex items-center gap-2">
          {/* <Image src={girl_1} alt="User avatar" width={20} height={20} /> */}
          <UserInfoSheet />
          <p className="text-sm">Posted by {data.authorId}</p>
          {/* <span className="text-slate-400 text-xs">
            12 hours ago
          </span> */}
        </div>

        <div>
          <div
            className="text-sm text-slate-600 font-light"
            dangerouslySetInnerHTML={{ __html: data.content }}
          ></div>
        </div>
        <div className="flex items-center text-slate-500 gap-4 font-semibold">
          <div className="flex items-center gap-1">
            <ThumbsUp className="cursor-pointer text-blue-500" onClick={handleUpvote} />
            <span className="text-xs text-slate-700 font-semibold">23</span>
            <ThumbsDown className="cursor-pointer text-red-500" onClick={handleDownVote} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Comment;
