import { useState, useEffect } from "react";
import axiosRequest from "@/utils/axiosRequest";
import { Badge } from "@/components/ui/badge";
import CustomBadge from "./CustomBadge";

// const topics = ["Java", "Spring boot", "Programming", "Spring security"];

const colors = [
  "bg-blue-500",
  "bg-red-500",
  "bg-green-500",
  "bg-orange-500",
  "bg-purple-500",
  "bg-yellow-500",
];

const FilterByTopicCard = ({ handleFilter }) => {
  const [topics, setTopics] = useState(null);

  useEffect(() => {
    axiosRequest
      .get("/topics")
      .then((res) => {
        if (res.status === 200) {
          console.log(res.data);
          setTopics(res.data?.content);
        }
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <div>
      <div className="flex flex-col w-full bg-white rounded-sm border border-slate-300 min-h-[100px]">
        <div className="w-full p-3 bg-slate-900 text-sm text-white font-semibold flex items-center rounded-t-sm">
          Filter by topic
        </div>
        <div className="flex flex-wrap gap-2 p-4">
          {topics?.map((e, i) => {
            const randint = Math.floor(Math.random() * colors.length);
            return (
              <Badge
                key={`${e?.name}-${i}`}
                className={`${colors[randint]} hover:${colors[randint]} p-2 cursor-pointer hover:opacity-80`}
                onClick={() => handleFilter(e?.name)}
              >
                {e?.name}
              </Badge>
            );
          })}
        </div>
      </div>
    </div>
  );
};

export default FilterByTopicCard;
