import { MessageSquare, Flag, ThumbsDown, ThumbsUp } from "lucide-react";
import { CustomPopover } from "./CustomPopover";
import ReportForm from "./ReportForm";
import { useUserInfoStore } from "@/store/userInfoStore";

import Link from "next/link";
import TimeAgo from "timeago-react";

const PostCard = ({ data }) => {
  const userinfo = useUserInfoStore((state) => state.userInfo);




  return (
    <div className="flex flex-col sm:flex-row gap-3 w-full bg-white rounded-sm border border-slate-300 p-3">
      {/* <div className="text-slate-500 flex flex-col items-center h-full bg-slate-50 p-2">
        <ThumbsUp className="cursor-pointer text-blue-500" />
        <span className="my-2">23</span>
        <ThumbsDown className="cursor-pointer text-red-500" />
      </div> */}

      <div className="flex flex-col gap-2 py-2">
        <div className="flex items-center gap-2">
          <p className="text-slate-600 font-semibold text-xs capitalize">
            {data?.topicNames.join(" - ")}
          </p>
          <span className="text-slate-400 text-xs">
            Posted by {data?.fullname} <TimeAgo datetime={data?.createdAt} />
          </span>
        </div>

        <p className="text-md text-slate-800 font-semibold">{data.title}</p>
        <Link className="cursor-pointer" href={`/posts/${data.id}`}>
          <div
            className="text-sm text-slate-600 font-light"
            dangerouslySetInnerHTML={{ __html: data.content }}
          ></div>
        </Link>
        <div className="flex items-center text-slate-500 gap-4 font-semibold">
          <Link
            href={`/posts/${data.id}`}
            className="flex items-center gap-1 cursor-pointer"
          >
            <MessageSquare size={16} />{" "}
            <span className="text-sm">Comments</span>
          </Link>
          <div className="flex items-center gap-1 cursor-pointer">
            <CustomPopover
              triggerButton={
                <div className="flex items-center gap-1 cursor-pointer">
                  <Flag size={16} /> <span className="text-sm">Report</span>
                </div>
              }
              body={<ReportForm postId={data.id} userId={userinfo.id} />}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default PostCard;
