/*
 * Copyright (C) 2011, Ketan Padegaonkar <KetanPadegaonkar@gmail.com>
 * and other copyright owners as documented in the project's IP log.
 *
 * This program and the accompanying materials are made available
 * under the terms of the Eclipse Distribution License v1.0 which
 * accompanies this distribution, is reproduced below, and is
 * available at http://www.eclipse.org/org/documents/edl-v10.php
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above
 *   copyright notice, this list of conditions and the following
 *   disclaimer in the documentation and/or other materials provided
 *   with the distribution.
 *
 * - Neither the name of the Eclipse Foundation, Inc. nor the
 *   names of its contributors may be used to endorse or promote
 *   products derived from this software without specific prior
 *   written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.eclipse.jgit.ant.tasks;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

/**
 * Checkout a branch or paths to the working tree.
 *
 * @see <a
 *      href="http://www.kernel.org/pub/software/scm/git/docs/git-checkout.html"
 *      >git-checkout(1)</a>
 */
public class GitCheckoutTask extends Task {

	private File src;
	private String branch;
	private boolean createBranch;
	private boolean force;

	/**
	 * Set the <code>src</code>
	 *
	 * @param src
	 *            the src to set
	 */
	public void setSrc(File src) {
		this.src = src;
	}

	/**
	 * Set <code>branch</code>
	 *
	 * @param branch
	 *            the initial branch to check out
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * Set if branch should be created if not yet existing
	 *
	 * @param createBranch
	 *            whether the branch should be created if it does not already
	 *            exist
	 */
	public void setCreateBranch(boolean createBranch) {
		this.createBranch = createBranch;
	}

	/**
	 * Set <code>force</code>
	 *
	 * @param force
	 *            if <code>true</code> and the branch with the given name
	 *            already exists, the start-point of an existing branch will be
	 *            set to a new start-point; if false, the existing branch will
	 *            not be changed
	 */
	public void setForce(boolean force) {
		this.force = force;
	}

	/** {@inheritDoc} */
	@Override
	public void execute() throws BuildException {
		CheckoutCommand checkout;
		try (Repository repo = new FileRepositoryBuilder().readEnvironment()
				.findGitDir(src).build();
			Git git = new Git(repo)) {
			checkout = git.checkout();
		} catch (IOException e) {
			throw new BuildException("Could not access repository " + src, e);
		}

		try {
			checkout.setCreateBranch(createBranch).setForceRefUpdate(force)
					.setName(branch);
			checkout.call();
		} catch (Exception e) {
			throw new BuildException("Could not checkout repository " + src, e);
		}
	}

}
