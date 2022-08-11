package com.board.domain.comment;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;






@RestController
public class CommentController {

	
	@Autowired
	private CommentService commentService;

	@GetMapping(value = "/comments/{bIdx}")
	public JsonObject getCommentList(@PathVariable("bIdx") Long bIdx, @ModelAttribute("params") CommentRequest params) {

		JsonObject jsonObj = new JsonObject();

		List<CommentRequest> commentList = commentService.getCommentList(params);
		if (CollectionUtils.isEmpty(commentList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTime()).create();
			JsonArray jsonArr = gson.toJsonTree(commentList).getAsJsonArray();
			jsonObj.add("commentList", jsonArr);
		}

		return jsonObj;
	}

	@RequestMapping(value = { "/comments", "/comments/{bIdx}" }, method = { RequestMethod.POST, RequestMethod.PATCH })
	public JsonObject registerComment(@PathVariable(value = "bIdx", required = false) Long idx,   @RequestBody CommentRequest params) {

		JsonObject jsonObj = new JsonObject();

		try {
			if (idx != null) {
				params.setCIdx(idx);
			}

			boolean isRegistered = commentService.registerComment(params);
			jsonObj.addProperty("result", isRegistered);

		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}

			return jsonObj;
	}
	
	@DeleteMapping(value = "/comments/{cIdx}")
	public JsonObject deleteComment(@PathVariable("cIdx")  Long cIdx) {

		JsonObject jsonObj = new JsonObject();

		try {
			boolean isDeleted = commentService.deleteComment(cIdx);
			
			jsonObj.addProperty("result", isDeleted);
			System.out.println(isDeleted);

		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}
		System.out.println(jsonObj);
		return jsonObj;
	}
	
	
}