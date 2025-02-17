import { CommentDTO } from "./commentDTO";

export interface ArticleDetail {
  id: number;
  title: string;
  content: string;
  createdAt: Date;
  authorId: number;
  authorUsername: string;
  themeId: number;
  themeName: string;
  comments: CommentDTO[];
}
